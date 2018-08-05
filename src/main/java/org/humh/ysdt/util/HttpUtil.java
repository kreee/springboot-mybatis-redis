package org.humh.ysdt.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class HttpUtil {

    public final static void main(String[] args) throws Exception {
    	String s = httpGet("https://api.weixin.qq.com/sns/jscode2session?appid=wxfe132e3c5321ac64&secret=d47ff5f6a8816e680ab7b28c6193a062&js_code=081WX5zl1TDfzl0bYJwl1bGRyl1WX5zv&grant_type=authorization_code");
    	Gson gson = new Gson();
    	JsonObject obj = gson.fromJson(s, JsonObject.class);
    	System.out.println("Executing request "+obj.get("errmsg"));
    }
    
    /**
     * get请求
     * @param url 请求url 后面携带参数
     * @return String
     * @throws IOException 
     */
    public static String httpGet(String url) throws IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String responseBody = null;

        try {
            HttpGet httpget = new HttpGet(url);
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            httpclient.close();
        }
    	return responseBody;
    }
}
