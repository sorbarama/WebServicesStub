package it.lutech.c2sense.pee.ws.client;

import it.lutech.c2sense.pee.ws.PeeWSImplProxy;
import it.lutech.c2sense.pee.ws.PeeWSProxy;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class PeeWSClient {
	
	public static void main(String[] args) {

        try {
            PeeWSImplProxy proxyService;
            System.out.println("#############################");
            System.out.println("#### Enter domain:");
            System.out.println("#### ");
            System.out.println("#### 1) http://c2sense-ie.ns0.it");
            System.out.println("#### 2) http://localhost:8080");
            System.out.println("#############################");
            System.out.println("#### ====> ");
            Scanner input = new Scanner(System.in);
            while(!(input.hasNext())){
                System.out.println("#### Invalid input. Enter (Y/N)");
            }
            int value = input.nextInt();
            switch (value) {
                case 1:
                    URL url = new URL("http://c2sense-ie.ns0.it/pee/ws/PeeWS?wsdl");
                    QName qname = new QName("http://ws.pee.c2sense.lutech.it/", "PeeWSImplService");
                    proxyService = new PeeWSImplProxy(url, qname);
                    break;
                default:
                    proxyService = new PeeWSImplProxy();
                    break;
            }

            PeeWSProxy peeWSProxy = proxyService.getPeeWSImplPort();

            System.out.println("#############################");
            System.out.println("#### Enter function:");
            System.out.println("#### ");
            System.out.println("#### 1) importProfile [ DemoProfile.bpmn20.xml ]");
            System.out.println("#### 2) deleteProfile");
            System.out.println("#### 3) startProfile");
            System.out.println("#### 4) completeUserTask");
            System.out.println("#############################");
            System.out.println("#### ====> ");
            input = new Scanner(System.in);
            while(!(input.hasNext())){
                System.out.println("#### Invalid input. Enter (Y/N)");
            }
            value = input.nextInt();

            switch (value) {
                case 1:
                    importProfile(peeWSProxy);
                    break;
                case 2:
                    deleteProfile(peeWSProxy);
                    break;
                case 3:
                    startProfile(peeWSProxy);
                    break;
			case 4:
				completeUserTask(peeWSProxy);
				break;
                default:
                    System.out.println("Invalid input [" + value + "]");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
	}

	private static void importProfile(PeeWSProxy peeWSProxy) {
		String bpmn_relative_path = "diagrams";
		String realPath = "./";
		String BPMN_FILE_EXT = ".bpmn20.xml";
		FileSystemUtil fileSystemUtil = new FileSystemUtil();
		List<String> xmlfile = fileSystemUtil.listFile(realPath + bpmn_relative_path, bpmn_relative_path, BPMN_FILE_EXT);
		System.out.println(xmlfile.size());
		for(String fileName: xmlfile){
			try {
                byte[] fileBytes = loadFile(fileName);
				System.out.println(peeWSProxy.importProfile(fileBytes, fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void deleteProfile(PeeWSProxy peeWSProxy) {
		System.out.println(peeWSProxy.deleteProfile("process", Boolean.TRUE));
	}

	private static void startProfile(PeeWSProxy peeWSProxy) {
		System.out.println(peeWSProxy.startProfile("process", "", ""));
	}

	private static void completeUserTask(PeeWSProxy peeWSProxy) {
		System.out.println((peeWSProxy.completeUserTask("processId", "taskDefinitionKey")));
	}

	private static byte[] loadFile(String fileName) throws IOException {
		File file = new File(fileName);
		FileInputStream fin = null;
		byte[] fileContent = null;
		try {
			// create FileInputStream object
			fin = new FileInputStream(file);

			fileContent = new byte[(int)file.length()];

			// Reads up to certain bytes of data from this input stream into an array of bytes.
			fin.read(fileContent);

			//create string from byte array
			String s = new String(fileContent);
			System.out.println("File content: " + s);
		}
		finally {
			// close the streams using close method
			try {
				if (fin != null) {
					fin.close();
				}
			}
			catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}

		return fileContent;
	}

}
