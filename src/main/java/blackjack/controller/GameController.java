package blackjack.controller;

import blackjack.domain.participants.Dealer;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.player.Player;
import blackjack.domain.participants.player.Players;
import blackjack.dto.PlayerDto;
import blackjack.dto.ProfitDto;
import blackjack.utils.Mapper;
import blackjack.utils.ResultCalculator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.function.Supplier;


public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardDeck cardDeck;

    private GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardDeck = CardDeck.create();
    }

    public static GameController of(InputView inputView, OutputView outputView) {
        return new GameController(inputView, outputView);
    }

    public void play(Players players) {
        Dealer dealer = createDealerWithCards();
        distributeCardsToPlayers(players);
        outputView.printCardDistribution(Mapper.toParticipantsDto(dealer, players));
        playersDraws(players);
        dealerDraws(dealer);
        ProfitDto profitDto = ResultCalculator.calculateProfit(dealer, players);
        outputView.printResult(Mapper.toParticipantsDto(dealer, players), profitDto);
    }

    private Dealer createDealerWithCards() {
        Cards cards = cardDeck.initialDraw();
        return Dealer.from(cards);
    }

    private void distributeCardsToPlayers(Players players) {
        players.initializeCards(cardDeck);
    }

    private void playersDraws(Players players) {
        players.getPlayers().forEach(player -> {
            PlayerDto playerDto = Mapper.toPlayerDto(player);
            askDraw(player, playerDto);
        });
    }

    private void askDraw(Player player, PlayerDto playerDto) {
        boolean drawAnotherCard;
        do {
            drawAnotherCard = readHit(playerDto);
            if (drawAnotherCard) {
                player.addCard(cardDeck.drawCard());
                PlayerDto updatedPlayerDto = Mapper.toPlayerDto(player);
                outputView.printUpdatedCardStatus(updatedPlayerDto);
            }
        } while (drawAnotherCard);
    }


    private boolean readHit(PlayerDto playerDto) {
        return readUserInput(() -> inputView.readHit(playerDto));
    }

    private <T> T readUserInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void dealerDraws(Dealer dealer) {
        boolean needsDraw = dealer.needsDraw();
        while (needsDraw) {
            outputView.printDealerDraw();
            dealer.addCard(cardDeck.drawCard());
            needsDraw = dealer.needsDraw();
        }
    }
}
