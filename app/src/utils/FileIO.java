package utils;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.mozilla.universalchardet.UniversalDetector;

public class FileIO {
    
    public static List<String> readFile(String name) {
        Path path = FileSystems.getDefault().getPath(".\\src", name);
        return readFile(path);
    }

    public static List<String> readFile(Path path) {
        String encoding = "";
        try {
            encoding = getEncoding(path.toFile().getCanonicalPath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if(encoding == null) {
            //System.err.println("Not found: " + path);
            encoding = "UTF-8";
        }
        if(!Charset.isSupported(encoding)) {
          System.err.println("Not found: " + encoding);
        }
        List<String> result = new ArrayList<String>();
        BufferedReader reader;
        try {
            FileInputStream is = new FileInputStream(path.toFile());
            reader = new BufferedReader(new InputStreamReader(is, encoding));
            //reader = Files.newBufferedReader(path, Charset.forName(encoding));
            String line = reader.readLine();
            while (line != null) {
                result.add(new String(line.getBytes(), "UTF-8"));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(path);
            e.printStackTrace();
        }
        return result;
    }
    
    private static String getEncoding(String file) {
        byte[] buf = new byte[4096];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // (1)
        UniversalDetector detector = new UniversalDetector(null);

        // (2)
        int nread;
        try {
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
              detector.handleData(buf, 0, nread);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // (3)
        detector.dataEnd();

        // (4)
        String encoding = detector.getDetectedCharset();
        return encoding;
    }
    
    public static List<String> readFile() {
        System.out.print("Please enter file name: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = null;
        try {
            name = br.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        List<String> result = new ArrayList<String>();
        Path path = FileSystems.getDefault().getPath(".\\src", name);
        BufferedReader reader;
        try {
            reader = Files.newBufferedReader(path, StandardCharsets.UTF_16);
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
