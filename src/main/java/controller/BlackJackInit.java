package controller;

import domain.user.Players;
import dto.PlayersRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackInit {
    private final InputView inputView;
    private final OutputView outputView;
    public BlackJackInit(InputView inputView, OutputView outputView){
        this.inputView = inputView;
        this.outputView = outputView;
    }
    public Players run(){
        List<String> playerNames = inputView.enterGamer();
        Map<String, Integer> playerSetting = playerNames.stream()
                .collect(Collectors.toMap(
                        oneName -> oneName,
                        inputView::enterBettingAmount
                ));
        outputView.initCard(playerNames);
        return Players.from(PlayersRequest.create(playerSetting));
    }
}
