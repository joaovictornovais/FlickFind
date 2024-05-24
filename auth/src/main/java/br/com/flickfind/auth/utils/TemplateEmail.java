package br.com.flickfind.auth.utils;

import br.com.flickfind.auth.domain.user.User;

public class TemplateEmail {

    public static String verificateAccount(User user) {
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"pt-BR\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Confirmação de Conta - FlickFind</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            color: #333;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #ffffff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            padding: 10px 0;\t\n" +
                "        }\n" +
                "        .header img {\n" +
                "            width: full;\n" +
                "\t    max-height: 120px;\n" +
                "        }\n" +
                "        .content {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .button {\n" +
                "            display: inline-block;\n" +
                "            margin: 20px auto;\n" +
                "            padding: 10px 20px;\n" +
                "            font-size: 16px;\n" +
                "            color: #fff;\n" +
                "            background-color: #007BFF;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            margin-top: 20px;\n" +
                "            font-size: 12px;\n" +
                "            color: #777;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <img src=\"https://media.istockphoto.com/id/1001414360/photo/multi-ethnic-teenage-friends-watching-tv-together-at-hangout-house.jpg?s=612x612&w=0&k=20&c=3npZe24UFEBNrEvuX6w0u0Rt_OBjXGp4D206Jpa-LCA=\" alt=\"FlickFind Logo\">\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <h1>Bem-vindo ao FlickFind!</h1>\n" +
                "            <p>Olá [[NOME]],</p>\n" +
                "            <p>Obrigado por se registrar no FlickFind. Para completar seu cadastro, por favor, clique no botão abaixo para confirmar seu e-mail:</p>\n" +
                "            <a href=\"[[URL]]\" class=\"button\">Confirmar E-mail</a>\n" +
                "            <p>Se você não se registrou no FlickFind, ignore este e-mail.</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 FlickFind. Todos os direitos reservados.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        text = text.replace("[[NOME]]", user.getFirstName());
        text = text.replace("[[URL]]",
                "http://localhost:8081/auth/activate/" + user.getId() + "?key=" + user.getVerificationCode());
        return text;
    }

}
