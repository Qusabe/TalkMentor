package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


public class TongueTwisters extends ExHandler implements Initializable {

    private boolean flag = true;

    HashMap<String, String[]> choiceDict = new HashMap<String, String[]>();
    ArrayList<String> TongueTwisters = new ArrayList<>(); //Динамический массив, который будет включать в себя скороговорки

    //Б
    String[] TTonB = {"<1>Белый снег, белый мел, белый заяц тоже бел. А вот белка не бела - белой даже не была.</1>\n",
            "<2>Дефибриллятор дефибриллировал дефибриллировал да не выдефибриллировал.</2>\n",
            "<3>Бредут бобры в сыры боры. Бобры храбры, а для бобрят добры.</3>\n",
            "<4>Дятел дуб долбил, долбил, продалбливал, да не продолбил и не выдолбил.</4>\n"};


    //Л
    String[] TTonL = {"<1>Карл украл у Клары кораллы, а Клара украла у Карла  кларнет.</1>\n",
            "<2>Рыла свинья белорыла, тупорыла, полдвора рылом изрыла, вырыла, подрыла.\n" +
                    " На то Хавронье и дано рыло, чтобы она рыла.\n</2>",
            "<3>Дятел дуб долбил, долбил, продалбливал, да не продолбил и не выдолбил.\n</3>",
            "<4>Полили ли лилию? Видели ли Лидию? Полили Лилию, видели Лидию.\n</4>",
            "<5>На мели мы налима лениво ловили. Меняли налима вы мне на линя.\n" +
                    " О любви не меня ли вы мило молили, и в туманы лимана манили меня.\n</5>",
            "<6>Лилии полили ли, иль увяли лилии?\n</6>"};


    //П
    String[] TTonP = {"<1>Когда-то галок поп пугая,\n" +
            "В кустах заметил попугая,\n" +
            "И говорит тут попугай:\n" +
            "«Пугать ты галок, поп, пугай.\n" +
            "Но, только галок, поп, пугая,\n" +
            "Не смей пугать ты попугая!»\n</1>",
            "<2>Вашему пономарю нашего пономаря не перепономаривать стать:\n" +
                    " наш пономарь вашего пономаря перепономарит, перевыпономарит.\n</2>"};


    //М
    String[] TTonM = {"<1>Вашему пономарю нашего пономаря не перепономаривать стать:\n" +
            "наш пономарь вашего пономаря перепономарит, перевыпономарит.\n</1>",
            "<2>На мели мы налима лениво ловили. Меняли налима вы мне на линя.\n" +
                    "О любви не меня ли вы мило молили, и в туманы лимана манили меня.\n</2>"};


    //Р
    String[] TTonR = {"<1>Баркас приехал в порт Мадрас.\n" +
            "Матрос принёс на борт матрас.\n" +
            "В порту Мадрас матрас матроса\n" +
            "Порвали в драке альбатросы.\n</1>",
            "<2>В недрах тундры выдры в гетрах\n" +
                    "Тырят в вёдра ядра кедров.\n" +
                    "Выдрав с выдры в тундре гетры,\n" +
                    "Вытру выдрой ядра кедра,\n" +
                    "Вытру гетрой выдре морду,\n" +
                    "Выдру — в тундру, ядра — в вёдра!\n</2>",
            "<3>Прокурор проколол дыроколом протокол.\n</3>",
            "<4>Четыре чёрненьких чумазеньких чертёнка чертили чёрными чернилами чертёж чрезвычайно чисто.\n</4>",
            "<5>Интервьюер интервента интервьюировал.\n</5>",
            "<6>Роман Кармен положил в карман роман Ромена Роллана и пошёл в \"Ромэн\" на \"Кармен\".\n</6>",
            "<7>Ехал грека через реку, видит грека - в реке рак. Сунул грека руку в реку, рак за руку греку - цап!\n</7>",
            "<8>Вашему пономарю нашего пономаря не перепономаривать стать: наш пономарь вашего пономаря\n" +
                    "перепономарит, перевыпономарит.\n</8>"};

    //С
    String[] TTonS = {"<1>Скороговорун скороговорил скоровыговаривал, что всех скороговорок\n " +
            "не перескороговоришь не перескоровыговариваешь, но заскороговорившись,\n " +
            "выскороговорил - что все скороговорки перескороговоришь, перескоровыговариваешь.\n " +
            "И прыгают скороговорки как караси на сковородке.\n</1>",
            "<2>Везёт Сенька Саньку с Сонькой на санках. Санки скок, Сеньку с ног, Соньку в лоб, все в сугроб.\n</2>",
            "<3>Коси, коса, пока роса, роса долой - и мы домой.\n</3>",
            "<4>Глядят подсолнухи на солнышко,\n" +
                    "А солнышко - на подсолнушки.\n" +
                    "\n" +
                    "Но подсолнухов много у солнышка,\n" +
                    "А солнышко одно у подсолнушка.\n" +
                    "\n" +
                    "Под солнцем подсолнух солнечно смеялся пока зрел.\n" +
                    "Созрел, подсох, склевался.\n</4>",
            "<5>Саша шустро сушит сушки.\n" +
                    "Сушек высушил штук шесть.\n" +
                    "И смешно спешат старушки\n" +
                    "Сушек Сашиных поесть.\n</5>",
            "<6>В семеро саней семеро Семёнов с усами уселись в сани сами.</6>"};


    //Ц
    String[] TTonC = {"<1>Жили-были три китайца\n" +
            "Як, Як-Ци-Драк и Як-Ци-Драк-Ци-Драк-Ци-Дрони.\n" +
            "Жили-были три китайки\n" +
            "Цыпа, Цыпа-Дрипа и Цыпа-Дрипа-Лимпомпони.\n" +
            "\n" +
            "Вот они переженились:  \n" +
            "Як на Цыпе Як-Ци-Драк на Цыпе-дрипе\n" +
            "Як-Ци-Драк-Ци-Драк-Ци-Дрони на Цыпе-Дрипе-Лимпомпони.\n" +
            "\n" +
            "И у них родились дети:\n" +
            "У Яка с Цыпой - Шах,\n" +
            "У Як-Цы-драка с Цыпой-дрипой - Шах-Шахмони,  \n" +
            "У Як-Ци-Драк-Ци-Драк-Ци-Дрони\n" +
            "С Цыпой-Дрипой-Лимпомпони -\n" +
            "Шах-Шахмони-Лимпомпони.\n</1>",
            "<2>Цыплёнок цапли цепко цеплялся за цеп.\n</2>"};


    //Шипящие(ш,ж,ч,щ)
    String[] TTonHissing = {"<1>Шесть мышат в камышах шуршат.\n</1>",
            "<2>В шалаше шуршит шелками жёлтый дервиш из Алжира и, жонглируя ножами, штуку кушает инжира.\n</2>",
            "<3>Шли сорок мышей, несли шестнадцать грошей, две мыши поплоше несли по два гроша.\n</3>",
            "<4>Даже шею, даже уши ты испачкал в чёрной туши. Становись скорей под душ. Смой с ушей под душем тушь.\n" +
                    " Смой и с шеи тушь под душем. После душа вытрись суше. Шею суше, суше уши, и не пачкай больше уши.\n</4>",
            "<5>Шёл Шишига по шоссе, шёл шуpша штанами. Шаг шагнёт, шепнёт: \"Ошибка\". Шевельнёт ушами.\n</5>",
            "<6>Два щенка щека к щеке щиплют щёку в уголке.\n</6>",
            "<7>Чешуя у щучки, щетинка у чушки.\n</7>",
            "<8>В луже, посредине рощи\n" +
                    "Есть у жаб своя жилплощадь.\n" +
                    "Здесь живёт ещё жилец -\n" +
                    "Водяной жук - плавунец.\n</8>"};

    public ArrayList plAnswer = new ArrayList();
    public ArrayList letters = new ArrayList();
    @FXML
    private TextField plChoice;
    @FXML
    private Label TTfield;
    @FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceDict.put("Б", TTonB);
        choiceDict.put("Л", TTonL);
        choiceDict.put("П", TTonP);
        choiceDict.put("М", TTonM);
        choiceDict.put("Р", TTonR);
        choiceDict.put("С", TTonS);
        choiceDict.put("Ц", TTonC);
        choiceDict.put("Ш", TTonHissing);
        choiceDict.put("Ч", TTonHissing);
        choiceDict.put("Ж", TTonHissing);
        choiceDict.put("Щ", TTonHissing);

    }

    public void toggleGame(ActionEvent event) {
        if (flag) {
            onButton(event);
        }
        else
            onButton(event);
    }

    public void toProfile(ActionEvent event) throws IOException {
        TongueTwisters.clear();
        plAnswer.clear();
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onButton(ActionEvent event) {
        TongueTwisters.clear();
        plAnswer.clear();
        try {
            String[] cache = plChoice.getText().split(", ");
            for (int i = 0; i < cache.length; i++) {
                plAnswer.add(i, cache[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < plAnswer.size(); i++) {
            builderTongueTwisters(TongueTwisters, choiceDict.get(plAnswer.get(i)));
        }

        for (String obj : TongueTwisters) {
            TTfield.setText(obj);
        }
    }

    public static ArrayList builderTongueTwisters(ArrayList allTT, String[] part) {
        for (int i = 0; i < part.length; i++)
            allTT.add(part[i]);
        return allTT;
    }


}