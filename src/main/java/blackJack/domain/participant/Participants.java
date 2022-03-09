package blackJack.domain.participant;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackJack.domain.WinOrLose;

public class Participants {

    private static final String ERROR_MESSAGE_DUPLICATE_PLAYER_NAME = "플레이어의 이름은 중복될 수 없습니다.";
    private static final String ERROR_MESSAGE_PLAYER_COUNT = "플레이어의 인원수는 1명 이상 7명 이하여야 합니다.";
    private static final int MINIMUM_COUNT = 1;
    private static final int MAXIMUM_COUNT = 7;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
        validateDuplicatePlayerName(players);
        validatePlayerCount(players);
        this.dealer = dealer;
        this.players = players;
    }

    private void validateDuplicatePlayerName(List<Player> players) {
        if (players.size() != new HashSet<>(players).size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATE_PLAYER_NAME);
        }
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() < MINIMUM_COUNT || players.size() > MAXIMUM_COUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_COUNT);
        }
    }

    public Map<Player, WinOrLose> calculateGameResult() {
        final Map<Player, WinOrLose> gameResult = new LinkedHashMap<>();

        for (Player player : players) {
            WinOrLose winOrLose = WinOrLose.calculateWinOrLose(player.calculateScore(), dealer.calculateScore());
            gameResult.put(player, winOrLose);
        }

        return gameResult;
    }
}
