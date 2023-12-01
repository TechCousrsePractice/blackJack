package controller;


import static domain.constant.GameConstant.DEALER_SHOWING_CARD_INDEX;
import static domain.constant.GameConstant.INITIAL_CARD_DRAW_NUMBER;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.util.CardSelector;
import dto.EachParticipantsCardStatusDto;
import dto.UserCardsDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import view.input.InputView;
import view.input.exception.InputException;
import view.output.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        Dealer dealer = Dealer.create();
        List<Player> players = generatePlayers();

        drawCard(dealer, players);
        notifyAllParticipantsGetCards(players);
        showEachParticipantsCardStatus(dealer.getCards(), players);
    }

    private List<Player> generatePlayers() {
        List<Player> players = new ArrayList<>();

        List<String> participantNames = getParticipantsNames();
        participantNames.forEach(
                participantName -> {
                    players.add(Player.of(participantName, getParticipantsBettingMoney(participantName)));
                }
        );

        return Collections.unmodifiableList(players);
    }

    private List<String> getParticipantsNames() {
        return readUserInput(() -> {
            outputView.askToInsertParticipantNames();
            return inputView.getParticipantNames();
        });
    }

    private Double getParticipantsBettingMoney(String participantName) {
        return readUserInput(() -> {
            outputView.askToInsertBettingMoney(participantName);
            return inputView.getParticipantBettingMoney();
        });
    }

    private void drawCard(Dealer dealer, List<Player> players) {
        IntStream.range(0, INITIAL_CARD_DRAW_NUMBER).forEach(
                drawCount -> {
                    dealer.addCard(CardSelector.selectCard());
                    players.forEach(player -> player.addCard(CardSelector.selectCard()));
                }
        );
    }

    public void notifyAllParticipantsGetCards(List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .toList();
        outputView.notifyALlParticipantsGetCards(playerNames);
    }

    public void showEachParticipantsCardStatus(List<Card> dealerCards, List<Player> players) {
        String oneOfDealerCardName = dealerCards.get(DEALER_SHOWING_CARD_INDEX).toString();
        List<UserCardsDto> userCardsDtos = new ArrayList<>();
        players.forEach(
                player -> {
                    List<String> userCardNames = player.getCards().stream()
                            .map(Card::toString)
                            .toList();
                    userCardsDtos.add(UserCardsDto.of(player.getName(), userCardNames));
                }
        );
        outputView.showEachParticipantsCardStatus(EachParticipantsCardStatusDto.of(oneOfDealerCardName, userCardsDtos));
    }

    private <T> T readUserInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (InputException e) {
                outputView.printError(e.getMessage());
            }
        }
    }


}