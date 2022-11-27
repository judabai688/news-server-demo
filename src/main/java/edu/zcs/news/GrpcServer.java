package edu.zcs.news;

import edu.zcs.news.service.NewsService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    private static final int port = 9999;
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(port).addService(new NewsService()).build().start();
        System.out.println(String.format("GRPC服务端启动成功，端口号：%d.",port));
        server.awaitTermination();//等待
    }
}
