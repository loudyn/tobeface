package com.tobeface.tgenius.infrastructure.wapi;

import java.io.IOException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptions;
import com.tobeface.tgenius.infrastructure.wapi.strategy.WeiboApiRequestStrategy;

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

	private final static Logger logger = LoggerFactory.getLogger(WeiboApiRequestPerformer.class);

	/**
	 * 
	 * @param req
	 * @return
	 */
	static WeiboApiResponse perform(WeiboApiRequest req, WeiboApiRequestStrategy strategy) {
		Preconditions.notNull(req);
		Preconditions.notNull(strategy);

		logger.debug("perform {}.", new Object[] { req });
		HttpUriRequest httpReq = WeiboApiRequestPerformer.transform(req);
		HttpEntity entity = null;
		try {

			HttpClient client = new DefaultHttpClient(connectionManager);
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);

			HttpResponse httpResp = client.execute(httpReq);
			entity = httpResp.getEntity();
			return strategy.continueExecute(req, new WeiboApiResponse(EntityUtils.toString(entity)));
		} catch (IOException e) {
			throw WeiboApiExceptions.newServiceUnavaliable(e);
		} catch (Exception e) {
			throw WeiboApiExceptions.newWeiboApiException(e);
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

			HttpPost post = new HttpPost(req.getUrl());
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (Entry<String, Object> entry : req.getParams().entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}

			post.setEntity(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));
			return post;
		} catch (Exception e) {
			throw Lang.uncheck(e);
		}
	}

	private WeiboApiRequestPerformer() {}
}
