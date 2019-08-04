package ec.com.java.challenge.bowling.parser;

import ec.com.java.challenge.bowling.game.IBowlingGame;
import ec.com.java.challenge.bowling.game.TenPinBowlingGame;
import ec.com.java.challenge.bowling.util.GameLineUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * <p>TenPinBowlingGameLineParser class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class TenPinBowlingGameLineParser implements IBowlingGameParser {
    private List<String> lines;

    /**
     * <p>Constructor for TenPinBowlingGameLineParser.</p>
     *
     * @param lines a {@link java.util.List} object.
     */
    public TenPinBowlingGameLineParser(List<String> lines) {
        this.lines = lines;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IBowlingGame createSingle() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IBowlingGame> createAll() {
        if (lines == null) {
            return Collections.emptyList();
        }
        Map<String, IBowlingGame> games = new HashMap<>();
        lines.stream().forEach(line -> {
            String playerName = GameLineUtil.playerName(line);
            IBowlingGame game;
            if (games.containsKey(playerName)) {
                 game = games.get(playerName);
            } else {
                game = createNewGame(playerName);
                games.put(playerName, game);
            }
            int pins = GameLineUtil.pins(line);
            game.roll(pins, GameLineUtil.isFoul(line));
        });
        return new ArrayList<>(games.values());
    }

    private IBowlingGame createNewGame(String playerName) {
        return new TenPinBowlingGame(playerName);
    }
}
