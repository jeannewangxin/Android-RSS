package fr.ws.reader.request;

import fr.ws.reader.app.Constants;
import fr.ws.reader.util.L;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.util.List;
import java.util.Map;

/**
 * 网络请求方法
 */
public class QMethod {

    public static final String TAG = "QMethod";

    /**
     * post请求
     *
     * @param url_name
     * @param map
     * @param id
     * @param listener
     */
    static void post(String url_name, Map<String, String> map, int id, QCallback listener) {
        //map.put("token", Constants.APP_TOKEN);
        String url = Constants.SEVER_ADDRESS + url_name;
        L.d(TAG, map.toString());
        OkHttpUtils.post().url(url).id(id).params(map).build().execute(listener);
    }

    static void postWithFile(String url_name, Map<String, String> map, int id, PostFormBuilder.FileInput fileInput, QCallback callback) {
        map.put("token", Constants.APP_TOKEN);
        String url = Constants.SEVER_ADDRESS + url_name;
        L.d(TAG, map.toString());
        OkHttpUtils.post().url(url).id(id).params(map).addFile(fileInput).build().execute(callback);
    }

    static void postWithFiles(String url_name, Map<String, String> map, int id, List<PostFormBuilder.FileInput> fileInputs, QCallback callback) {
        map.put("token", Constants.APP_TOKEN);
        String url = Constants.SEVER_ADDRESS + url_name;
        L.d(TAG, map.toString());
        OkHttpUtils.post().url(url).id(id).params(map).addFiles(fileInputs).build().execute(callback);
    }

    /**
     * get请求
     *
     * @param url_name
     * @param map
     * @param id
     * @param listener
     */
    static void get(String url_name, Map<String, String> map, int id, QCallback listener) {
        map.put("token", Constants.APP_TOKEN);
        String url = Constants.SEVER_ADDRESS + url_name;
        L.d(TAG, map.toString());
        OkHttpUtils.get().url(url).id(id).params(map).build().execute(listener);
    }
}
