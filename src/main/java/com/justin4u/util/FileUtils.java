package com.justin4u.util;

import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 * com.justin4u.util
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-16</pre>
 */
public class FileUtils {

    public static final boolean empty(String filename) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            pw.close();
        }
        return true;
    }

    public static final boolean empty2(String filename) {
        try (PrintWriter pw = new PrintWriter(filename)) {
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final byte[] downloadUrlAsBytes(String url) {
        try {
            URL website = new URL(url);
            ReadableByteChannel readChannel = Channels.newChannel(website.openStream());
            ByteArrayOutputStream output = new ByteArrayOutputStream(32 * 1024);
            WritableByteChannel writeChannel = Channels.newChannel(output);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while ((readChannel.read(buffer)) > 0 || buffer.position() != 0) {
                buffer.flip();
                writeChannel.write(buffer);
                buffer.compact();
            }

            return output.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    public static final boolean downloadUrlThenSaveFile(String url, String filePath) {
        try {
            URL website = new URL(url);
            ReadableByteChannel readChannel = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.getChannel().transferFrom(readChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static final String readContentFromFilePath(String filePath) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        String s;
        try {
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (sb == null) {
            return null;
        }
        return sb.toString();
    }

    public static final String readContentFromFilePath2(String filePath) {
        StringBuffer sb = new StringBuffer();
        try (Reader r = new FileReader(filePath);
             BufferedReader in = new BufferedReader(r)) {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {

        }

        return sb.toString();
    }

    public static final String readContentFromFilePath3(String filePath) {
        try {
            return Files.asCharSource(new File(filePath), Charset.defaultCharset()).read();
        } catch (IOException e) {
            return null;
        }
    }

    public static final String readResourceBundleByEncoding(String baseName, Charset charset) {
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        ResourceBundle prop = ResourceBundle.getBundle(baseName);
        String result = null;
        try {
            result = new String(prop.getString("defaultTunnelName").getBytes("ISO-8859-1"), charset.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        FileUtils.empty("/tmp/a.txt");
    }


}
