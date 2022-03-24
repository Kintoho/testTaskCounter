import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitSetUniqueIpCounter implements Parser {

    private final Logger logger = Logger.getLogger("BitSetUniqueIpCounter");
    private final int[] score = new int[255];
    private final BitSet bitSetLow = new BitSet(Integer.MAX_VALUE); // 0 - 2_147_483_647
    private final BitSet bitSetHi = new BitSet(Integer.MAX_VALUE); // 2_147_483_648 - 4_294_967_295
    private long counter = 0;

    private void registerLongValue(long longValue, int[] arrValue) {
        int intValue = (int) longValue;
        BitSet workingSet = bitSetLow;

        if (longValue > Integer.MAX_VALUE) {
            intValue = (int) (longValue - Integer.MAX_VALUE);
            workingSet = bitSetHi;
        }

        if (!workingSet.get(intValue)) {
            counter++;
            workingSet.set(intValue);
            score[arrValue[0]]++;
        }
    }

    @Override
    public int[] countUniqueByteIp(String fileName){
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            long linesProcessed = 0;
            String line;
            while ((line = in.readLine()) != null && counter <= NUMBER_OF_IP_ADDRESSES) {
                registerLongValue(Parser.toLongValue(line), Parser.toByteValue(line));
                linesProcessed++;
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File '" + fileName + "' not found", e);
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException occurs", e);
        }
        return score;
    }

    @Override
    public long countUniqueIp(String fileName) {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            long linesProcessed = 0;
            String line;
            while ((line = in.readLine()) != null && counter <= NUMBER_OF_IP_ADDRESSES) {
                registerLongValue(Parser.toLongValue(line),  Parser.toByteValue(line));
                //registerByteValue(UniqueIpCounter.toByteValue(line));
                linesProcessed++;
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File '" + fileName + "' not found", e);
            counter = -1;
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException", e);
            counter = -1;
        }
        return counter;
    }
}