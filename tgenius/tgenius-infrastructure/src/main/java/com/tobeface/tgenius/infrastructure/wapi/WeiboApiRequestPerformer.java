package com.tobeface.tgenius.infrastructure.wapi;

import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.tobeface.modules.lang.Lang;

/**
 * 
 * @author loudyn
 * 
 */
final class WeiboApiRequestPerformer {

	private static PoolingClientConnectionManager connectionManager;
	static {

		try {

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			};

			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			registry.register(new Scheme("https", 443, ssf));

			connectionManager = new PoolingClientConnectionManager(registry);
			connectionManager.setMaxTotal(200);
			connectionManager.setDefaultMaxPerRoute(100);
		} catch (Exception e) {
			throw Lang.uncheck(e);
		}
	}

	/**
	 * 
	 * @param req
	 * @return
	 */
	static WeiboApiResponse perform(WeiboApiRequest req) {

		HttpUriRequest httpReq = WeiboApiRequestPerformer.transform(req);
		HttpEntity entity = null;
		try {

			HttpClient client = new DefaultHttpClient(connectionManager);
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);

			HttpResponse resp = client.execute(httpReq);
			entity = resp.getEntity();
			return new WeiboApiResponse(EntityUtils.toString(entity));
		} catch (Exception e) {
			throw Lang.uncheck(e);
		} finally {
			EntityUtils.consumeQuietly(entity);
		}
	}

	/**
	 * 
	 * @param req
	 * @return
	 */
	private static HttpUriRequest transform(WeiboApiRequest req) {
		try {

			if (req.isGetVerb()) {
				return new HttpGet(req.getUrl());
			}

			HttpPost post = new HttpPost();
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			for (Entry<String, Object> entry : req.getParams().entrySet()) {
				postParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}

			post.setEntity(new UrlEncodedFormEntity(postParams, Charset.forName("UTF-8")));
			return post;
		} catch (Exception e) {
			throw Lang.uncheck(e);
		}
	}

	private WeiboApiRequestPerformer() {}
}
