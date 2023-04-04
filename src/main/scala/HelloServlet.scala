import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class HelloServlet extends HttpServlet {
    override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
        resp.setContentType("text/html;charset-utf8")
        resp.setStatus(HttpServletResponse.SC_OK)
        resp.getWriter().println("Hello, world!")
    }
}
