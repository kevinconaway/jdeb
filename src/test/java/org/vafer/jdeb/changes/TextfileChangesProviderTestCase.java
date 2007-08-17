package org.vafer.jdeb.changes;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.vafer.jdeb.descriptors.PackageDescriptor;

public final class TextfileChangesProviderTestCase extends TestCase {

	public void testParsing() throws Exception {

		final String input =
			" * change1\n" +
			" * change2\n" +
			"   bla bla\n" +
			"release date=13.01.2007, version=12324, urgency=low, by=tcurdt@joost.com\n" +
			" * change1\n" +
			" * change2\n" +
			"   bla bla\n" +
			"release date=10.01.2007, version=10324, urgency=low, by=tcurdt@joost.com\n" +
			" * change1\n" +
			" * change2\n" +
			"   bla bla\n";
		
		final PackageDescriptor descriptor = new PackageDescriptor();
		descriptor.set("Package", "package");
		descriptor.set("Version", "version");
		descriptor.set("Distribution", "distribution");
		descriptor.set("Date", "date");
		
		final TextfileChangesProvider provider = new TextfileChangesProvider(new ByteArrayInputStream(input.getBytes("UTF-8")), descriptor);
		final ChangeSet[] changeSets = provider.getChangesSets();
		
		assertNotNull(changeSets);
		assertEquals(3, changeSets.length);		
		
		System.out.println(changeSets[0].toString());
		System.out.println(changeSets[1].toString());
		System.out.println(changeSets[2].toString());
		
	}
}
