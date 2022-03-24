public class IpCounterApp {

    private static String parseFileName(String[] args) {
        String fileName = null;
        if (args.length == 1) {
            fileName = args[0];
        }
        return fileName;
    }

    public static void main(String[] args) {
        String fileName = parseFileName(args);
        if (fileName == null) {
            System.out.println("Wrong arguments.");
            return;
        }

        Parser counter = new BitSetUniqueIpCounter();
        long numberOfUniqueIp = counter.countUniqueIp(fileName);
        int[] numberOfUniqueByteIp = counter.countUniqueByteIp(fileName);
        if (numberOfUniqueIp != -1) {
            System.out.println("Unique count: " + numberOfUniqueIp);
            System.out.println("First byte  |  Unique count");
            for(int i = 0; i< 255; i++){
                if(numberOfUniqueByteIp[i] != 0)
                System.out.println((i) + "\t \t" + numberOfUniqueByteIp[i]);
            }
        } else {
            System.out.println("Some errors here. Check log for details.");
        }
    }
}