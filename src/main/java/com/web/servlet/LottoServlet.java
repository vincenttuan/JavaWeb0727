package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 電腦選號 Servlet
public class LottoServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // UTF-8 編碼設定
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 1~49 取6組, 不可重複
        Set<Integer> set = new LinkedHashSet<>();
        Random r = new Random();
        while(set.size() < 6) {
            set.add(r.nextInt(49) + 1);
        }
        // 顯示在網頁上
        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<header>");
        out.print("<title>本期樂透電腦選號</title>");
        out.print("</header>");
        out.print("<body>");
        out.print("<h1>");
        out.print("本期樂透開獎號碼 : ");
        out.print(set.toString());
        out.print("</h1>");
        out.print("</body>");
        out.print("</html>");
    }
    
}