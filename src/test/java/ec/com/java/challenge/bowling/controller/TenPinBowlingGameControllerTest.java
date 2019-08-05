package ec.com.java.challenge.bowling.controller;

import ec.com.java.challenge.bowling.input.FileMockUtil;
import ec.com.java.challenge.bowling.output.ConsoleGameOutputWriter;
import ec.com.java.challenge.bowling.output.IGameOutputWriter;
import ec.com.java.challenge.bowling.util.Constants;
import ec.com.java.challenge.bowling.util.GameInputType;
import ec.com.java.challenge.bowling.util.GameOutputType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TenPinBowlingGameControllerTest {

    private static IBowlingGameController controller;

    @BeforeClass
    public static void init() {
        Map<String, String> extraInputInfo = new HashMap<>();
        extraInputInfo.put(Constants.EXTRA_INFO_FILE_KEY, FileMockUtil.FILE_PATH);
        controller = new TenPinBowlingGameController(GameInputType.FILE, GameOutputType.CONSOLE, extraInputInfo);
    }

    @Test
    public void endToEndIntegration1() {
        fillFile1();
        controller.run();
        IGameOutputWriter outputWriter = ((TenPinBowlingGameController) controller).outputWriter;
        List<String> result = ((ConsoleGameOutputWriter) outputWriter).getWriteResult();
        List<String> expected = new ArrayList<>();
        expected.add("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        expected.add("Jeff");
        expected.add("Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1");
        expected.add("Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167");
        expected.add("John");
        expected.add("Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0");
        expected.add("Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void endToEndIntegration2() {
        fillFile2();
        controller.run();
        IGameOutputWriter outputWriter = ((TenPinBowlingGameController) controller).outputWriter;
        List<String> result = ((ConsoleGameOutputWriter) outputWriter).getWriteResult();
        List<String> expected = new ArrayList<>();
        expected.add("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        expected.add("Name1");
        expected.add("Pinfalls\t6\t/\t6\t3\t2\t4\t5\t/\t\tX\t3\t2\t6\t/\t2\t2\t4\t3\tX\t7\t2");
        expected.add("Score\t\t16\t\t25\t\t31\t\t51\t\t66\t\t71\t\t83\t\t87\t\t94\t\t113");
        expected.add("Name2");
        expected.add("Pinfalls\t8\t/\t\tX\t7\t2\t4\t/\t4\t2\t6\t/\t3\t3\t7\t/\t\tX\t3\t/\tX");
        expected.add("Score\t\t20\t\t39\t\t48\t\t62\t\t68\t\t81\t\t87\t\t107\t\t127\t\t147");
        Assert.assertEquals(expected, result);
    }

    private void fillFile1() {
        FileMockUtil.createMockFile();
        FileMockUtil.writeToFile(lines1());
    }

    private void fillFile2() {
        FileMockUtil.createMockFile();
        FileMockUtil.writeToFile(lines2());
    }

    private List<String> lines1() {
        List<String> list = new ArrayList<>();

        list.add("Jeff 10");
        list.add("John 3");
        list.add("John 7");
        list.add("Jeff 7");
        list.add("Jeff 3");
        list.add("John 6");
        list.add("John 3");
        list.add("Jeff 9");
        list.add("Jeff 0");
        list.add("John 10");
        list.add("Jeff 10");
        list.add("John 8");
        list.add("John 1");
        list.add("Jeff 0");
        list.add("Jeff 8");
        list.add("John 10");
        list.add("Jeff 8");
        list.add("Jeff 2");
        list.add("John 10");
        list.add("Jeff F");
        list.add("Jeff 6");
        list.add("John 9");
        list.add("John 0");
        list.add("Jeff 10");
        list.add("John 7");
        list.add("John 3");
        list.add("Jeff 10");
        list.add("John 4");
        list.add("John 4");
        list.add("Jeff 10");
        list.add("Jeff 8");
        list.add("Jeff 1");
        list.add("John 10");
        list.add("John 9");
        list.add("John 0");
        return list;
    }

    private List<String> lines2() {
        List<String> list = new ArrayList<>();
        list.add("Name1 6");
        list.add("Name1 4");
        list.add("Name2 8");
        list.add("Name2 2");
        list.add("Name1 6");
        list.add("Name1 3");
        list.add("Name2 10");
        list.add("Name1 2");
        list.add("Name1 4");
        list.add("Name2 7");
        list.add("Name2 2");
        list.add("Name1 5");
        list.add("Name1 5");
        list.add("Name2 4");
        list.add("Name2 6");
        list.add("Name1 10");
        list.add("Name2 4");
        list.add("Name2 2");
        list.add("Name1 3");
        list.add("Name1 2");
        list.add("Name2 6");
        list.add("Name2 4");
        list.add("Name1 6");
        list.add("Name1 4");
        list.add("Name2 3");
        list.add("Name2 3");
        list.add("Name1 2");
        list.add("Name1 2");
        list.add("Name2 7");
        list.add("Name2 3");
        list.add("Name1 4");
        list.add("Name1 3");
        list.add("Name2 10");
        list.add("Name1 10");
        list.add("Name1 7");
        list.add("Name1 2");
        list.add("Name2 3");
        list.add("Name2 7");
        list.add("Name2 10");
        return list;
    }
}
