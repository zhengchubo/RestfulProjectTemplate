package com.justin4u.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class IoUtil {

    public static void main(String[] args) {
        //create();
        //readFileByStream();
        //copyFileByStream();
        //writeThenReadObject();
        //handleDataStream();
        //printWrite();
        printFileByStream();
    }

    public static void create() {
        File file = new File("/tmp/tmpFile.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long totalSpace = file.getTotalSpace();
        file.mkdirs();
        //boolean deleted = file.delete();
        String parent = file.getParent();
        boolean isExist = file.exists();
        System.out.println("Total Space: " + totalSpace / 1024 / 1024 / 1024 + " G");
        //System.out.println("File deleted: " + deleted);
        System.out.println("Parent of file: " + parent);
        System.out.println("Is file still existed yet: " + isExist);
    }

    public static void readFileByStream() {
        InputStreamReader reader = null;
        int count = 0;
        try {
            reader = new InputStreamReader(new FileInputStream("/tmp/tmpFile.txt"));
            while (reader.read() != -1) {
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The file length is " + count);
    }

    public static void copyFileByStream() {
        byte[] bytes = new byte[512];
        FileInputStream in = null;
        FileOutputStream out = null;
        int read = 0;
        try {
            in = new FileInputStream("/tmp/test.pdf");
            out = new FileOutputStream("/tmp/out.pdf");
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFileByBufferedStream() {
        byte[] bytes = new byte[512];
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        int read = 0;
        try {
            in = new BufferedInputStream(new FileInputStream("/tmp/test.pdf"));
            out = new BufferedOutputStream(new FileOutputStream("/tmp/out.pdf"));
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeThenReadObject() {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream("/tmp/person.txt"));
            out.writeObject(new Person("Justin", 30));
            out.writeObject(new Person("Tom", 10));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            in = new ObjectInputStream(new FileInputStream("/tmp/person.txt"));
            System.out.println(in.readObject());
            System.out.println(in.readObject());
            System.out.println(in.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void handleDataStream() {
        String filename = "/tmp/person.txt";
        DataInputStream in = null;
        DataOutputStream out = null;
        Person[] persons = {new Person("Justin", 30), new Person("Tom", 3)};
        try {
            out = new DataOutputStream(new FileOutputStream(filename));
            for (Person p : persons) {
                out.writeUTF(p.getName());
                out.writeInt(p.getAge());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            in = new DataInputStream(new FileInputStream(filename));
            for (int i = 0; i < persons.length; i++) {
                System.out.println(in.readUTF());
                System.out.println(in.readInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printFileByStream() {
        String filename = "/tmp/a.txt";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            byte[] bytes = new byte[512];
            int length = 0;
            while ((length = fis.read(bytes)) != -1) {
                //System.out.write(bytes, 0, length);
                System.out.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printWrite() {
        String filename = "/tmp/person.txt";
        char[] chars = new char[512];
        int read;
        FileReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new FileReader(filename);
            writer = new PrintWriter(System.out);
            while ((read = reader.read(chars)) != -1) {
                writer.write(chars, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Person implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "My name is " + name + ", I am " + age + " years old.";
    }
}
