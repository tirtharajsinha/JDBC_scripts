import java.io.BufferedReader;
import java.io.InputStreamReader;


public class bank {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("--------------------------");
            System.out.println("    1. Crete new account");
            System.out.println("    2. Close a account");
            System.out.println("    3. Add balance");
            System.out.println("    4. Show account details");
            System.out.println("    5. Exit");
            System.out.println("--------------------------");
            System.out.print("Select one of the operations : ");
            int n = Integer.parseInt(br.readLine());
            switch (n) {
                case 1:
                    insert_into_accounts.main(null);
                    break;
                case 2:
                    remove_account.main(null);
                    break;
                case 3:
                    deposit_into_account.main(null);
                    break;
                case 4:
                    show_account.main(null);
                    break;
                default:
                    System.out.println("Exiting bank....");
                    System.exit(0);
            }
        }

    }
}
