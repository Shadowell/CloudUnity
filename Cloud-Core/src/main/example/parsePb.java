public static void parsePb(byte[] bytes) {
    ResponsePB.Response response = ResponsePB.Response.parseFrom(bytes);
    boolean hasNextPage = response.getHasNextPage();
    String dirtag = response.getDirtag();

}