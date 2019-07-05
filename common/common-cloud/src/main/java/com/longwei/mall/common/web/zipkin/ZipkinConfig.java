///*
//package com.zjrcinfo.zjguahao.common.web.zipkin;
//
//
//import com.github.kristofa.brave.Brave;
//import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
//import com.github.kristofa.brave.Sampler;
//import com.github.kristofa.brave.SpanCollector;
//import com.github.kristofa.brave.http.DefaultSpanNameProvider;
//import com.github.kristofa.brave.http.HttpSpanCollector;
//import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
//import com.github.kristofa.brave.servlet.BraveServletFilter;
//import okhttp3.OkHttpClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ZipkinConfig {
//    @Value("${spring.zipkin.service.name}")
//    private String serviceName;
//    @Value("${spring.zipkin.baseUrl}")
//    private String baseUrl;
//    @Value("${spring.zipkin.connectTimeout}")
//    private int connectTimeout;
//    @Value("${spring.zipkin.readTimeout}")
//    private int readTimeout;
//    @Value("${spring.zipkin.flushInterval}")
//    private int flushInterval;
//    @Value("${spring.zipkin.compression.enabled}")
//    private boolean compressionEnabled;
//
//
//
//    @Bean
//    public SpanCollector spanCollector() {
//        HttpSpanCollector.Config config = HttpSpanCollector.Config.builder().connectTimeout(connectTimeout).readTimeout(readTimeout)
//                .compressionEnabled(compressionEnabled).flushInterval(flushInterval).build();
//        return HttpSpanCollector.create(baseUrl, config, new EmptySpanCollectorMetricsHandler());
//    }
//
//
//    @Bean
//    public Brave brave(SpanCollector spanCollector){
//        Brave.Builder builder = new Brave.Builder(serviceName);  //指定state
//        builder.spanCollector(spanCollector);
//        builder.traceSampler(Sampler.ALWAYS_SAMPLE);
//        Brave brave = builder.build();
//        return brave;
//    }
//
//    @Bean
//    public BraveServletFilter braveServletFilter(Brave brave){
//        BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),brave.serverResponseInterceptor(),new DefaultSpanNameProvider());
//        return filter;
//    }
//
//    @Bean
//    public OkHttpClient okHttpClient(Brave brave){
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new BraveOkHttpRequestResponseInterceptor(brave.clientRequestInterceptor(), brave.clientResponseInterceptor(), new DefaultSpanNameProvider()))
//                .build();
//        return client;
//    }
//
//}
//*/
