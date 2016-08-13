package com.alibaba.weex.constants;

public class Constants {

    //  public static final String BUNDLE_URL = "http://t.cn?_wx_tpl=http://h5.waptest.taobao.com/app/weextc031/build/TC__Home.js";
    public static final String BUNDLE_URL = "http://t.cn?_wx_tpl=http://g.tbcdn.cn/weex/weex-tc/0.1.0/build/TC__Home.js";
    public static final String WEEX_SAMPLES_KEY = "?weex-samples";
    public static final String WEEX_TPL_KEY = "_wx_tpl";

    //hot refresh
    public static final int HOT_REFRESH_CONNECT = 0x111;
    public static final int HOT_REFRESH_DISCONNECT = HOT_REFRESH_CONNECT + 1;
    public static final int HOT_REFRESH_REFRESH = HOT_REFRESH_DISCONNECT + 1;
    public static final int HOT_REFRESH_CONNECT_ERROR = HOT_REFRESH_REFRESH + 1;

    public static class WeexIndex {
        //Weex 入口地址
//    public static String WEEX_INDEX = "file://assets/index.js";
        private static String WEEX_INDEX = "http://30.10.140.135:8081/weex_tmp/h5_render/test.js?wsport=8082";

        public static boolean isUrl() {
            return WEEX_INDEX.startsWith("http");
        }

        public static String getWeexIndex() {
            return WEEX_INDEX;
        }

        public static void setWeexIndex(String weexIndex) {
            if (weexIndex.startsWith("http")) {
                WEEX_INDEX = weexIndex;

            } else {
                WEEX_INDEX = String.format("file://assets/%s", weexIndex);
            }
        }
    }
}
