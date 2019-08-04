package ec.com.java.challenge.bowling.parser;

import ec.com.java.challenge.bowling.game.IBowlingGame;
import ec.com.java.challenge.bowling.game.TenPinBowlingGame;
import ec.com.java.challenge.bowling.util.GameLineUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TenPinBowlingGameLineParser implements IBowlingGameParser{
    private List<String> lines;

    public TenPinBowlingGameLineParser(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public IBowlingGame createSingle() {
        return null;
    }

    @Override
    public List<IBowlingGame> createAll() {
        List<IBowlingGame> games = new ArrayList<>();
        if(lines != null) {
            lines.stream().forEach(l-> {
                String playerName = GameLineUtil.playerName(l);
                int pins = GameLineUtil.pins(l);
                IBowlingGame game = games.stream().filter(g-> Objects.equals(playerName, g.playerName())).findAny().orElse(createNewGame(playerName));
                game.roll(pins);
                if(!games.contains(game))
                    games.add(game);
            });

        }
        return games;
    }

    private IBowlingGame createNewGame(String playerName) {
        return new TenPinBowlingGame(playerName);
    }
}
