package cn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	private static Socket socket;
	private static BufferedReader sysBr;
	private static BufferedWriter bw;
	private static BufferedReader br;
	static {
		try {
			socket = new Socket("localhost", 9990);
			sysBr = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class SysRead implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				String line = "";
				while ((line = sysBr.readLine()) != null) {
					System.out.println(line);
					bw.write(line);
					bw.flush();
					bw.newLine();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static class SocketRead implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				String line = "";
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		SysRead sysRead = new SysRead();
		Thread th1 = new Thread(sysRead);
		th1.start();
		SocketRead socketRead = new SocketRead();
		Thread th2 = new Thread(sysRead);
		th2.start();
	}
}
