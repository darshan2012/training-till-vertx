package fileioExamples;

import java.io.*;

public class FileIOPractice {
    public void fileIOStreamExample(){
//        System.err.println("error");


        try ( FileOutputStream fout = new FileOutputStream("./textout.txt");){
            fout.write(65);
            String s = " Hello World";
            byte b[] = s.getBytes();
            fout.write(b);

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(FileInputStream fin = new FileInputStream("./textout.txt")){
            int i = fin.read();
            System.out.println(i);
            while((i = fin.read()) != -1)
                System.out.print((char)i);
        } catch (FileNotFoundException e) {
            System.out.println("FIle not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void bufferedIOStreamExamples(){

        try(FileOutputStream fout = new FileOutputStream("./textout.txt")) {
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            String s = "THis is the new text of the FIle.\nTHis is second line.";
            byte b[] = s.getBytes();
            bout.write(b);
            bout.flush();

        } catch (FileNotFoundException e) {
            System.out.println("FIle not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(FileInputStream fin = new FileInputStream("./textout.txt")){
            BufferedInputStream bin = new BufferedInputStream(fin);
            int i;
            while((i = bin.read()) != -1)
                System.out.print((char)i);
            System.out.println();
            bin.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void bufferedReaderWritedExample(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./textout.txt"))) {
            String s = "New Content for the file.";
            writer.write(s);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
        try(BufferedReader reader = new BufferedReader(new FileReader("./textout.txt"))){
            String s = reader.readLine();
            System.out.println(s);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void fileClassExample(){
        File f = new File("./");
        System.out.println(f.listFiles());

    }
}
