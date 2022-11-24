package demo.executorservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements AutoCloseable {
	
	private ServerSocket serverSocket;

	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		final List<PrintWriter> writers = new ArrayList<PrintWriter>();
		ExecutorService executorService = Executors.newFixedThreadPool(
				20 * Runtime.getRuntime().availableProcessors());
		executorService.execute(() -> {
			while (!serverSocket.isClosed()) {
				try {
					final Socket socket = serverSocket.accept();
					final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					final PrintWriter writer = new PrintWriter(socket.getOutputStream());
					executorService.execute(() -> {
						synchronized (writers) {
							writers.add(writer);
						}
						writer.println("Type a message or 'exit' to quit.");
						try {
							while (!socket.isClosed()) {
								reader.lines().forEach(l -> {
									if ("exit".equals(l)) {
										try {
											socket.close();
										} catch (IOException e) {
										}
									} else {
										synchronized (writers) {
											writers.stream()
												.filter(w -> w != writer)
												.forEach(w -> {
												w.println(l);
												w.flush();
											});
										}
									}
								});
							}
						} catch (Exception e) {
						} finally {
							synchronized (writers) {
								writers.remove(writer);
							}
						}
					});
				} catch (IOException e) {
				}
			}
		});
	}

	@Override
	public void close() throws Exception {
		serverSocket.close();
	}

}
