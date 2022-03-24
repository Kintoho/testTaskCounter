public interface Parser {

    long NUMBER_OF_IP_ADDRESSES = 256L * 256 * 256 * 256;
    long countUniqueIp(String fileName);
    int[] countUniqueByteIp(String fileName);

    static long toLongValue(String ipString) {
        String[] octets = ipString.split("\\.");
        return Long.parseLong(octets[0]) << 24 |
                Long.parseLong(octets[1]) << 16 |
                Long.parseLong(octets[2]) <<  8 |
                Long.parseLong(octets[3]);
    }

    static int[] toByteValue(String ipString) {
        String[] octets = ipString.split("\\.");
        int [] ipArray = new int[4];
        ipArray[0] = Integer.parseInt(octets[0]);
        ipArray[1] = Integer.parseInt(octets[1]);
        ipArray[2] = Integer.parseInt(octets[2]);
        ipArray[3] = Integer.parseInt(octets[3]);
        return ipArray;
    }
}