package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("Player 생성 테스트")
    void createValidPlayer() {
        assertThat(new Player("rookie")).isNotNull();
    }

    @ParameterizedTest(name = "플레이어의 이름 공백인 경우 검증 테스트")
    @ValueSource(strings = {" ", ""})
    void checkPlayerName(String value) {
        assertThatThrownBy(() -> new Player(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 '딜러'인 경우 검증 테스트")
    void checkProhibitName() {
        assertThatThrownBy(() -> new Player("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 '딜러'일 수 없습니다.");
    }


    @Test
    @DisplayName("플레이어의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalsePlayerNextTurn() {
        Card card1 = Card.from(Suit.SPADE, Denomination.JACK);
        Card card2 = Card.from(Suit.HEART, Denomination.JACK);
        Card card3 = Card.from(Suit.SPADE, Denomination.TWO);
        Player player = new Player("kei", Set.of(card1, card2, card3));

        assertThat(player.hasNextTurn()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 가능한 경우 테스트")
    void hasTruePlayerNextTurn() {
        Card card1 = Card.from(Suit.SPADE, Denomination.JACK);
        Card card2 = Card.from(Suit.HEART, Denomination.JACK);
        Player player = new Player("kei", Set.of(card1, card2));

        assertThat(player.hasNextTurn()).isTrue();
    }
}
