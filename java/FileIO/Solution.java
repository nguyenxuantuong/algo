import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("input.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			bw.write(line);	
			//bw.write('\n');
			bw.newLine();
		}

		bw.close();
		scanner.close();
	}	
}
