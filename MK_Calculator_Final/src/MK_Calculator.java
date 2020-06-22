import java.time.LocalDate;
import javax.swing.JOptionPane;

public class MK_Calculator {

    private final LocalDate currentDate = LocalDate.now();
    private static final int YEARS_FOR_MK_PE = 2;
    private static final int YEARS_FOR_MK_DE = 3;

    public static void main(String[] args) {
        new MK_Calculator().init();
    }

    private void init() {
        while (true) {
            LocalDate diorismos = getDiorismos();
            diorismos = getProyphresia(diorismos);
            String category = JOptionPane.showInputDialog("KATHGORIA (DE/PE)");
            if (category.equals("DE") || category.equals("de")) {
                int mk = findMKDE(diorismos);
                diorismos = diorismos.plusYears((mk - 1) * YEARS_FOR_MK_DE);
                JOptionPane.showMessageDialog(null, diorismos.getDayOfMonth() + "/" + diorismos.getMonthValue() + "/" + diorismos.getYear() + " -> MK" + mk);
            } else if (category.equals("PE") || category.equals("pe")) {
                diorismos = getMetaptyxiako(diorismos);
                int mk = findMKPE(diorismos);
                diorismos = diorismos.plusYears((mk - 1) * YEARS_FOR_MK_PE);
                JOptionPane.showMessageDialog(null, diorismos.getDayOfMonth() + "/" + diorismos.getMonthValue() + "/" + diorismos.getYear() + " -> MK" + mk);
            }
        }
    }

    private LocalDate getDiorismos() {
        String input = JOptionPane.showInputDialog("HMEROMHNIA DIORISMOU (DD/MM/YYYY)");
        String[] input_split = input.split("/");
        return LocalDate.of(Integer.parseInt(input_split[2]), Integer.parseInt(input_split[1]), Integer.parseInt(input_split[0]));
    }

    private LocalDate getProyphresia(LocalDate date) {
        String input = JOptionPane.showInputDialog("PROYPHRESIA (Y/M/D)");
        if (input.equals("")) {
            return date;
        }
        String[] input_split = input.split("/");
        date = date.minusYears(Integer.parseInt(input_split[0]));
        date = date.minusMonths(Integer.parseInt(input_split[1]));
        date = date.minusDays(Integer.parseInt(input_split[2]));
        return date;
    }

    private LocalDate getMetaptyxiako(LocalDate date) {
        String input = JOptionPane.showInputDialog("METAPTYXIAKO (NAI/OXI)");
        if (input.equals("NAI") || input.equals("nai")) {
            return date.minusYears(4);
        }
        return date;
    }

    private int findMKPE(LocalDate date) {
        int mk = 1;
        while (date.isBefore(currentDate)) {
            date = date.plusYears(YEARS_FOR_MK_PE);
            mk = mk + 1;
        }
        return mk;
    }

    private int findMKDE(LocalDate date) {
        int mk = 1;
        while (date.isBefore(currentDate)) {
            date = date.plusYears(YEARS_FOR_MK_DE);
            mk = mk + 1;
        }
        return mk;
    }
}