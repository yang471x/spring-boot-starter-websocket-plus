package com.icoder0.websocket.core.model;import com.alibaba.fastjson.annotation.JSONField;import com.icoder0.websocket.core.constant.WsBusiCode;import lombok.AccessLevel;import lombok.Builder;import lombok.Data;import lombok.extern.slf4j.Slf4j;/** * @author bofa1ex * @since 2020/6/30 */@Slf4j@Data@Builder(access = AccessLevel.PRIVATE)public class WsOutboundBean<T> implements WsOutboundBeanSpecification {    /* 消息序号, 如果自定义下行数据格式, 必须强制性要求该字段 */    @JSONField    private Long sequence;    @JSONField(ordinal = 1)    private Integer code;    @JSONField(ordinal = 2)    private T content;    @JSONField(ordinal = 3)    private String message;    @Override    public Long sequence() {        return sequence;    }    @Override    public void setSequence(Long sequence) {        this.sequence = sequence;    }    public static WsOutboundBeanBody status(WsBusiCode code) {        return new WsOutboundBeanBody(code);    }    public static <T> WsOutboundBean<T> ok(T body) {        return new WsOutboundBeanBody(WsBusiCode.OK).body(body);    }    public static WsOutboundBeanBody ok() {        return new WsOutboundBeanBody(WsBusiCode.OK);    }    public static class WsOutboundBeanBody {        private final WsBusiCode code;        public WsOutboundBeanBody(WsBusiCode code) {            this.code = code;        }        public <T> WsOutboundBean<T> body(T body) {            return WsOutboundBean.<T>builder()                    .code(code.getCode())                    .content(body)                    .build();        }        public <T> WsOutboundBean<T> message(String message) {            return WsOutboundBean.<T>builder()                    .code(code.getCode())                    .message(String.format(code.getZhMsg(), message))                    .build();        }    }}