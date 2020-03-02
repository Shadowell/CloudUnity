
public static void writeResponseToPbFile(String pbfilepath, ResponseJson responseJson) {
        File fproto = new File(pbfilepath);
        if (!fproto.exists()) {
        try {
        fproto.createNewFile();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        //build response
        //构造builder
        ResponsePB.Response.Builder responseBuilder = ResponsePB.Response.newBuilder();
        //填充数据
        responseBuilder.setHasNextPage(resultJson.hasNextPage);
        responseBuilder.setDirtag(resultJson.dirtag);
        ...//此处省略若干行
        //结束 build
        ResponsePB.Response response = responseBuilder.build();
        //写文件
        try {
        FileOutputStream foProto = new FileOutputStream(fproto);
        response.writeTo(foProto);
        foProto.close();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
}