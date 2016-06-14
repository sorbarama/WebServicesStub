package it.lutech.c2sense.pee.ws.client;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * User:      massimo
 * Date:      08/04/14
 */
public class FileSystemUtil {

    /**
     * Lists the contents of the folder specified by the concatenation of the absolute path and that relative,
     * filtering for a certain extension
     * @param absolutePath
     * @param relativePath
     * @param ext
     * @return
     */
    public List<String> listFile(String absolutePath, String relativePath, String ext) {

        List<String> lista = new ArrayList<>();

        GenericExtFilter filter = new GenericExtFilter(ext);

        File dir = new File(absolutePath);

        if(!dir.isDirectory()){
            System.out.println("Directory does not exists : " + absolutePath);
            return lista;
        }

        // list out all the file name and filter by the extension
        String[] list = dir.list(filter);

        if (list.length == 0) {
            System.out.println("no files end with : " + ext);
            return lista;
        }

        for (String file : list) {
            String temp = relativePath + File.separator + file;
            lista.add(temp);

            System.out.println("file : " + temp);

        }
        return lista;
    }

    public List<String> listFolderName(String path) {

        List<String> lista = new ArrayList<>();

        File dir = new File(path);

        if(!dir.isDirectory()){
            System.out.println("Directory does not exists : " + path);
            return lista;
        }

        // list out all the file name and folder
        String[] list = dir.list();

        if (list.length == 0) {
            System.out.println("nothing found in: " + path);
            return lista;
        }

        for (String folder : list) {
            if(new File(path + folder).isDirectory()) {
                System.out.println("folder : " + folder);
                lista.add(folder);
            }
        }
        return lista;
    }

    public class GenericExtFilter implements FilenameFilter {

        private String ext;

        public GenericExtFilter(String ext) {

            this.ext = ext;
        }

        public boolean accept(File dir, String name) {

            return (name.endsWith(this.ext));
        }
    }
}
