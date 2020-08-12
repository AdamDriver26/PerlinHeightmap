
/**
 * @author Adam Driver
 *
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		try {
//			Config.write();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Config param = null;
		try {
			param = Config.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(param.name + ",[" + param.dim[0] + "," + param.dim[1] + "]," + param.seaLevel);
	}

}
