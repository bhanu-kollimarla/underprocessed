package com.rudram.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;

public class XML2JSON {

	public static void main(String[] args) {

		String filePath = "input.xml";

		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			System.out.println("File exits.");
		}

		InputStream input = XML2JSON.class.getResourceAsStream(filePath);
		OutputStream output = System.out;
		/*
		 * If we want to insert JSON array boundaries for multiple elements, we
		 * need to set the <code>autoArray</code> property. If our XML source
		 * was decorated with <code>&lt;?xml-multiple?&gt;</code> processing
		 * instructions, we'd set the <code>multiplePI</code> property instead.
		 * With the <code>autoPrimitive</code> property set, element text gets
		 * automatically converted to JSON primitives (number, boolean, null).
		 */
		JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build();
		try {
			/*
			 * Create reader (XML).
			 */
			XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);

			/*
			 * Create writer (JSON).
			 */
			XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);

			/*
			 * Copy events from reader to writer.
			 */
			writer.add(reader);

			/*
			 * Close reader/writer.
			 */
			reader.close();
			writer.close();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*
			 * As per StAX specification, XMLEventReader/Writer.close() doesn't
			 * close the underlying stream.
			 */
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
