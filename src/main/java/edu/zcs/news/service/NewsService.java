package edu.zcs.news.service;

import edu.zcs.news.proto.NewsProto;
import edu.zcs.news.proto.NewsServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Date;

public class NewsService extends NewsServiceGrpc.NewsServiceImplBase {
    @Override
    public void list(NewsProto.NewsRequest request, StreamObserver<NewsProto.NewsResponse> responseObserver) {
        String date = request.getDate();
        NewsProto.NewsResponse newList = null;
        try {
            NewsProto.NewsResponse.Builder newListBuilder = NewsProto.NewsResponse.newBuilder();
            for (int i = 1; i < 100; i++) {
                NewsProto.News news = NewsProto.News.newBuilder().setId(i)
                        .setContent(date + "当日新闻内容" + i)
                        .setTitle("新闻标题" + i)
                        .setCreateTime(new Date().getTime())
                        .build();
                newListBuilder.addNews(news);
            }
            newList = newListBuilder.build();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
        finally {
            //返回数据，不是return
            responseObserver.onNext(newList);
        }
        //通知网络，本次处理完毕了
        responseObserver.onCompleted();
    }
}
