package com.csobrero.challenge.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.Validate;

import com.csobrero.challenge.bean.Leg;
import com.csobrero.challenge.bean.Ticket;
import com.csobrero.challenge.rs.FlightFilterResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.xml.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * Utils class for prototype. It shoud avoid this kind of classes on final development.
 * 
 * 
 * @author Christian
 *
 */
public class Utils {

	public static ByteArrayOutputStream JaxbMarshall(Object object) {
		ByteArrayOutputStream baos = null;
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			baos = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(object, baos);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		return baos;
	}

	public static byte[] generateByteCode(BarcodeFormat format,
			ImageFormat imageFormat, ImageSize imageSize, String contents) {

		Validate.notNull(format);
		Validate.notNull(imageFormat);
		Validate.notNull(imageSize);
		Validate.notEmpty(contents);

		Map<EncodeHintType, ?> hints = new HashMap<EncodeHintType, Object>();

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		try {
			BitMatrix matrix = new QRCodeWriter().encode(contents, format,
					imageSize.getWidth(), imageSize.getHeight(), hints);

			MatrixToImageWriter.writeToStream(matrix, imageFormat.getType(),
					buffer);

		} catch (Exception e) {
		}

		return buffer.toByteArray();

	}

	public static byte[] encodeBase64(byte[] b) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStream b64os;
		try {
			b64os = MimeUtility.encode(baos, "base64");
			b64os.write(b);
			b64os.close();
		} catch (Exception e) {
			throw new RuntimeException("Should handle this", e);
		}
		return baos.toByteArray();
	}

	public static byte[] decodeBase64(byte[] b) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		InputStream b64is = MimeUtility.decode(bais, "base64");
		byte[] tmp = new byte[b.length];
		int n = b64is.read(tmp);
		byte[] res = new byte[n];
		System.arraycopy(tmp, 0, res, 0, n);
		return res;
	}

	@SuppressWarnings("deprecation")
	public static String buildResponse(FlightFilterResponse response) {
		StringBuilder content = new StringBuilder();
		if (response != null && !response.getTickets().isEmpty()) {
			for (Ticket ticket : response.getTickets()) {
				if (ticket != null) {
					content.append("[Price:" + ticket.getPrice() + ";");
					for (Leg leg : ticket.getLegs()) {
						content.append("Flight:" + leg.getFlight().getCode()
								+ ";");
						content.append("From:" + leg.getOrigin().getIataCode()
								+ ";");
						content.append("To:"
								+ leg.getDestination().getIataCode() + ";");
						content.append("Departing:"
								+ leg.getDeparting().getDate() + ";");
						content.append("Arriving:"
								+ leg.getArriving().getDate() + ";");
					}
					content.append("]");
				}
				else {
					content.append("[]");
				}
			}
		}
		return content.toString();
	}

}
