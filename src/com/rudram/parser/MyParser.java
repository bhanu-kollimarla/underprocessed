package com.rudram.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;

public class MyParser {

	public static void main(String[] args) {

		String filePath = "C:\\Users\\Jay Modi\\Downloads\\Log Files\\Ka_AirCaraibes_RST_0123_20170329022218Z_SYSCFG_CVM.xml";
		filePath = "input.xml";
		// filePath = "pom.xml";

		JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).prettyPrint(true).build();
		XMLOutputFactory xmlOutputFactory = new JsonXMLOutputFactory(config);
		XMLStreamWriter writer = null;
		StringWriter stringWriter = null;

		try {
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(new FileReader(filePath));
			stringWriter = new StringWriter();
			writer = xmlOutputFactory.createXMLStreamWriter(stringWriter);

			// Start the document
			writer.writeStartDocument();

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				switch (event.getEventType()) {

				case XMLStreamConstants.START_ELEMENT: {
					StartElement startElement = event.asStartElement();
					String qName = startElement.getName().getLocalPart();
					// Start the element
					writer.writeStartElement(qName);

					@SuppressWarnings("unchecked")
					Iterator<Attribute> attributes = event.asStartElement().getAttributes();
					while (attributes.hasNext()) {
						Attribute attribute = attributes.next();
						// Write each attribute
						writer.writeAttribute(attribute.getName().toString(), attribute.getValue().toString());

						// writer.writeStartElement(attribute.getName().toString());
						// writer.writeCharacters(attribute.getValue().toString());
						// writer.writeEndElement();
					}
					break;
				}
				case XMLStreamConstants.CHARACTERS: {

					Characters characters = event.asCharacters();
					// Write the actual data
					writer.writeCharacters(characters.getData());
					// if (!characters.getData().trim().isEmpty()) {
					// writer.writeCharacters(characters.getData());}
					break;
				}
				case XMLStreamConstants.END_ELEMENT: {
					// End the element
					writer.writeEndElement();
					break;
				}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// Program ends here if you have incomplete xml.
		} finally {
			try {
				// End the document
				writer.writeEndDocument();
				writer.close();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
		System.out.println(stringWriter.toString());
	}
}