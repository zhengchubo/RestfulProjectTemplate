package com.justin4u.test;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * com.justin4u.test
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-04-29</pre>
 */
public class SshTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String raw = "/newsview/default.aspx?newscode=20190625020008684701";
        String enc = URLEncoder.encode(raw, StandardCharsets.UTF_8.toString());
        System.out.println(enc.equals("%2Fnewsview%2Fdefault.aspx%3Fnewscode%3D20190625020008684701"));
    }

    private static int test() {
        int i = 0;
        try{
            i = Integer.parseInt("a");
            i = 1;
            return i;
        }catch (Exception e){
            i = 2;
            return i;
        }finally{
            i = 3;
            return i;
        }
    }

    public static void main2(String[] args) {
        String hostname = "10.1.90.36";
        String username = "root";
        String password = "asdf";
        try {
            Connection conn = new Connection(hostname);
            conn.connect();
//            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            boolean isAuthenticated = conn.authenticateWithPublicKey(username, new File("/Users/justin/.ssh/id_rsa2"), null);
            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");
            Session sess = conn.openSession();
            sess.execCommand("uname -a && date && uptime && who");
            System.out.println("Here is some information about the remote host:");
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            br.close();
            sess.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }
}
