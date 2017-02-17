package empresa;

import java.io.*;

public class GestionEmpleados {
	private GestionDepartamentos d=new GestionDepartamentos();
	private BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
	private File fempleado=new File(".\\files\\empleados.txt");
	private File ftemporal=new File(".\\files\\temporal.txt");
	
	
	public GestionEmpleados(GestionDepartamentos d) {
		super();
		this.d = d;
	}
	public void menu() throws IOException{
		int opcion=0;
		do {
			 System.out.println("1. añadir empleado");
			 System.out.println("2. mostrar un empleado");
			 System.out.println("3. modificar empleado");
			 System.out.println("4. borrar empleado");
			 System.out.println("5. listar empleados");
			 System.out.println("0. salir");
			 try{
				 System.out.println("seleccione una opcion");
				 opcion=Integer.parseInt(teclado.readLine());
			 }catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero de 0 a 5");
				opcion=-1;
			 }
			 
			 switch (opcion) {
			case 1:
				addEmpleado();
				break;
			case 2:
				listarEmpleados();
				break;

			case 3:
				setEmpleado();
				break;

			case 4:
				borrarEmpleado();
				break;
			case 5:
				listarEmpleados();
				break;

			case 0:
				System.out.println("has salido del menu");
				break;
			default:
				System.out.println("debe introducir un numero entre 0 y 4");
				break;
			}
		} while (opcion!=0);	

	}
	private void addEmpleado() throws NumberFormatException, IOException {
		boolean seguir=true;
		int empnum=-1;
		do{
			try{
				System.out.println("Dame un numero de empleado");
				empnum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero.");
			}
		}while(seguir);
		//buscar si existe
		if(buscarEmpleado(empnum)){
			System.out.println("Ya existe un empleado con numero "+empnum);
			return;
		}
		Empleado e=new Empleado();
		e.pedirEmpleado(empnum);
		BufferedReader br;
		try{
			br=new BufferedReader(new FileReader(fempleado));
		}catch(FileNotFoundException ex){
			BufferedWriter bwr=new BufferedWriter(new FileWriter(fempleado));
			bwr.close();
			br=new BufferedReader(new FileReader(fempleado));
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(ftemporal));
		String linea=br.readLine();
		while(linea!=null){
			bw.write(linea);
			bw.newLine();
			linea=br.readLine();
		}
		//añadir la nueva linea con el nuevo empleado
		linea=e.crearLinea();
		bw.write(linea);
		bw.flush();
		bw.close();
		br.close();
		fempleado.delete();
		ftemporal.renameTo(fempleado);
	}
	
	private void listarEmpleados() throws IOException  {
		try {
			Empleado e= new Empleado();
			BufferedReader br= new BufferedReader(new FileReader(fempleado));
			String linea;
			do{
				linea=br.readLine();
				if(!(linea!=null)){
					break;
				}
				//descomponemos la linea y la mostramos
				e.descomponerLinea(linea);
				System.out.println(e);
			}while(true);

			br.close();
		} catch (FileNotFoundException e1) {
			System.out.println("No existe ningún empleado");
		}
	}
	private boolean buscarEmpleado(int empnum) throws IOException{
		try {
			Empleado e=new Empleado();
			BufferedReader br=new BufferedReader(new FileReader(fempleado));
			String linea=br.readLine();
			while (linea!=null) {
				e.descomponerLinea(linea);
				if (empnum==e.getEmpnum()) {
					br.close();
					return true;
				}
				br.readLine();
			}
			br.close();
			return false;
		} catch (FileNotFoundException exc) {
			return false;
		}
	}
	private void setEmpleado() {
		// TODO Apéndice de método generado automáticamente
		
	}
	private void borrarEmpleado() {
		// TODO Apéndice de método generado automáticamente
		
	}
}
