import io.fabric8.kubernetes.api.model.Service
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import scala.collection.JavaConverters._

class HomeServlet(services: Seq[Service]) extends HttpServlet {
    override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
        resp.setContentType("text/html;charset-utf8")
        resp.setStatus(HttpServletResponse.SC_OK)

        val tableRows = services.map { svc =>
            val metadata = svc.getMetadata()
            val name = metadata.getLabels.asScala
                .get("sparkoperator.k8s.io/app-name")
                .orElse(metadata.getLabels().asScala.get("app"))
                .getOrElse(metadata.getName())

            val port = svc.getSpec().getPorts().asScala.find(_.getName() == "spark-ui").get

            s"""
            <tr>
            <td>$name</td>
            <td>${metadata.getNamespace()}</td>
            <td>${port.getPort()}</td>
            </tr>
            """
        }

        val html = s"""
            |<html>
            |<head><title>Spark UI Proxy</title></head>
            |<body>
            |<table>
            |<tr><th>App Name</th><th>Namespace</th><th>Ports</th></tr>
            |${tableRows.mkString("\n")}
            |</table>
            |</body>
            |</html>
        """.stripMargin

        resp.getWriter().print(html)
    }
}
