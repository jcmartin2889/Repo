package com.misys.equation.uxp.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.misys.equation.common.utilities.EquationLogger;
import com.misys.uxp.framework.api.service.IUXRequestMessage;
import com.misys.uxp.framework.api.service.IUXResponseMessage;
import com.misys.uxp.framework.common.utilities.UXException;
import com.misys.uxp.framework.common.utilities.UXFactory;
import com.misys.uxp.framework.impl.service.UXResponseMessage;

public class EqBranchForward
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqBranchForward.java 306 2013-04-16 13:46:09Z jonathan.perkins $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EqBranchForward.class);

	/**
	 * Static initialisation block
	 */
	static
	{
		UXFactory.registerGsonTypeAdapter(UXResponseMessage.class, new UXResponseMessageDeserializer());
	}

	/**
	 * Forwards a UX request to another server
	 * 
	 * @param server
	 * @param request
	 * 
	 * @return A UXResponseMessage
	 */
	public IUXResponseMessage forwardUXRequest(final String server, IUXRequestMessage request)
	{

		HashMap<String, String> hmBody = request.getRequestItem().getRequestBody();
		// Indicate that the request should be handled by the DataCentre plug-in in Data Centre mode
		hmBody.put(EQServicePlugin.BODY_SERVER_TYPE, EQServicePlugin.SERVER_TYPE_DATA_CENTRE);

		String requestJson = UXFactory.getJsonString(request.getRequestItem());
		NameValuePair[] requestNameValuePair = new NameValuePair[] { new NameValuePair("requestItem", requestJson) };
		String responseString = httpForward(server, requestNameValuePair);
		return parseResponse(responseString);
	}

	private static String httpForward(final String server, final NameValuePair[] request)
	{
		StringBuffer response = new StringBuffer();
		final HttpClient client = new HttpClient();
		final PostMethod post = new PostMethod(server);
		post.setRequestBody(request);

		try
		{
			client.executeMethod(post);

			Map<String, Header> headers = new HashMap<String, Header>();
			for (Header headerItem : post.getResponseHeaders())
			{
				headers.put(headerItem.getName(), headerItem);
				LOG.info("Header name [" + headerItem.getName() + "] value [" + headerItem.getValue() + "]");
			}

			Header contentEncodingHeader = headers.get("Content-Encoding");
			if (contentEncodingHeader != null && contentEncodingHeader.getValue().equals("gzip"))
			{
				InputStream gzis = new GZIPInputStream(post.getResponseBodyAsStream());
				InputStreamReader reader = new InputStreamReader(gzis);
				BufferedReader in = new BufferedReader(reader);
				String readed;
				while ((readed = in.readLine()) != null)
				{
					response.append(readed);
				}
			}
			else
			{
				// Assume text
				response.append(post.getResponseBodyAsString());
			}

			switch (post.getStatusCode())
			{
				case 200: // OK (the request has succeeded)
				{
					LOG.info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
					break;
				}
				default:
					LOG.warn("Invalid response code (" + post.getStatusCode() + ") from UXPserver!");
					LOG.info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
					break;
			}
		}
		catch (final IOException e)
		{
			throw new UXException(e);
		}
		finally
		{
			post.releaseConnection();
		}

		return response.toString();
	}

	private static UXResponseMessage parseResponse(String response)
	{
		UXResponseMessage result = null;
		LOG.info(response);
		Object o = UXFactory.getObjectFromJson(response, UXResponseMessage.class);

		if (o instanceof UXResponseMessage)
		{
			result = (UXResponseMessage) o;
		}

		return result;
	}

	/**
	 * Checks whether the supplied UXResponseMessage is returning an Exception and returns that exception text if so.
	 * 
	 * @param uxResponseMessage
	 * @return A String containing the exception text, or null if a positive response
	 */
	public String getResponseException(IUXResponseMessage uxResponseMessage)
	{
		String result = null;
		if ("ex".equals(uxResponseMessage.getResponseItem().getHeader().get("mtype")))
		{
			if (uxResponseMessage.getResponseItem() != null && uxResponseMessage.getResponseItem().getResponseBody() != null)
			{
				result = uxResponseMessage.getResponseItem().getResponseBody().get("uxsd");
			}
			if (result == null)
			{
				throw new UXException("UXResponse message was of ex type, but did not include exception details");
			}
		}
		return result;
	}
}
