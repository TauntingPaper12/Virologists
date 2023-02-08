package projlab;

import projlab.commands.*;

import java.util.*;

public class Prototype {
    private static LinkedHashMap<String, Object> objects = new LinkedHashMap<>();

    public static void main(String[] args) {
        objects.clear();
        Scanner s = new Scanner(System.in);
        HashMap<String, ICommand> commands = new HashMap<>();
        commands.put("Create", new CreateCommand());
        commands.put("List", new ListCommand());
        commands.put("Load", new LoadCommand());
        commands.put("Save", new SaveCommand());
        commands.put("AddSteppable", new AddSteppableCommand());
        commands.put("Tick", new TickCommand());
        commands.put("SetRandom", new SetRandomCommand());
        commands.put("Exit", new ExitCommand());
        commands.put("Command", new CommandCommand());

        while (s.hasNextLine()) {
            String line = s.nextLine();
            if ("".equals(line.trim())) break;

            String[] temp = line.split(" ");
            if (commands.keySet().contains(temp[0])) {
                String[] _args = null;
                if (temp.length > 1) {
                    _args = Arrays.copyOfRange(temp, 1, temp.length, String[].class);
                }
                commands.get(temp[0]).run(_args);
            }
        }
    }

    public static HashMap<String, Object> getObjects() {
        return objects;
    }

    public static void setObjects(LinkedHashMap<String, Object> _objects) {
        objects = _objects;
    }

    public static void addObject(Object object) {
        Set<String> keys = objects.keySet();
        for (int i = 1; true; i++) {
            String key = object.getClass().getSimpleName() + i;
            if (!keys.contains(key)) {
                objects.put(key, object);
                break;
            }
        }
    }
}
