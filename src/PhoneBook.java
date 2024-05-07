import java.util.*;

public class PhoneBook {

    public static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ваш выбор: ");
            System.out.println(
                    "1 - добавить контакт\n2 - удалить контакт\n3 - найти контакт\n4 - посмотреть всех\n0 - выход");
            String userChoice = scanner.nextLine();
            if (userChoice.equals("1")) {
                System.out.println("Введите имя: ");
                String name = scanner.nextLine();
                System.out.println("Введите номер: ");
                Integer number = scanner.nextInt();
                add(name, number);

            } else if (userChoice.equals("2")) {
                System.out.println("Кого удаляем?");
                for (HashMap.Entry<String, ArrayList<Integer>> entry : phoneBook.entrySet()) {
                    System.out.println(entry.getKey());
                }
                String nameToRemove = scanner.nextLine();
                delete(nameToRemove);
            } else if (userChoice.equals("3")) {
                System.out.print("Кого ищем? ");
                String nameToFind = scanner.nextLine();
                search(nameToFind);
            } else if (userChoice.equals("4")) {
                getPhoneBook();
            } else if (userChoice.equals("0")) {
                break;
            } else {
                System.out.println("Что-то не то ввели, попробуйте заново");
            }
        }
        scanner.close();
    }

    public static void add(String name, Integer phoneNum) {

        ArrayList<Integer> numbers = new ArrayList<>();
        if (phoneBook.containsKey(name) == false) {
            numbers.add(phoneNum);
        } else {
            numbers = phoneBook.get(name);
            numbers.add(phoneNum);
        }
        phoneBook.put(name, numbers);
    }

    public static void delete(String nameToRemove) {

        if (phoneBook.containsKey(nameToRemove) == false) {
            System.out.println("Нет такого");
        } else {
            phoneBook.remove(nameToRemove);
        }
    }

    public static void search(String nameToFind) {

        if (phoneBook.containsKey(nameToFind) == false) {
            System.out.println("Нет такого");
        } else {
            System.out.println(nameToFind + ": " + phoneBook.get(nameToFind));
        }
    }

    public static void getPhoneBook() {

        ArrayList<ArrayList<Object>> listToShow = new ArrayList<>();
        for (HashMap.Entry<String, ArrayList<Integer>> entry : phoneBook.entrySet()) {
            ArrayList<Object> contactToShow = new ArrayList<>();
            contactToShow.add(entry.getKey());
            contactToShow.addAll(entry.getValue());
            listToShow.add(contactToShow);
        }

        Collections.sort(listToShow, new Comparator<ArrayList<Object>>() {
            public int compare(ArrayList<Object> a1, ArrayList<Object> a2) {
                return a2.size() - a1.size();
            }
        });

        for (int i = 0; i < listToShow.size(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(listToShow.get(i).get(0));
            builder.append(": ");
            for (int j = 1; j < listToShow.get(i).size() - 1; j++) {
                builder.append(listToShow.get(i).get(j));
                builder.append(", ");
            }
            builder.append(listToShow.get(i).get(listToShow.get(i).size() - 1));
            System.out.println(builder.toString());
        }
    }
}