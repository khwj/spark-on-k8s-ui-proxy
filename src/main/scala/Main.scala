import io.fabric8.kubernetes.api.model.Service
import io.fabric8.kubernetes.api.model.ServicePort
import io.fabric8.kubernetes.client.DefaultKubernetesClient
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

import scala.collection.JavaConverters._

object Main extends App {
    val k8s = new DefaultKubernetesClient
    val server = new Server(8080)
    val handler = new ServletContextHandler(ServletContextHandler.SESSIONS)
    handler.setContextPath("/")
    server.setHandler(handler)

    val namespaces = Seq("spark-apps")
    val services = namespaces
        .flatMap { ns =>
            k8s.services()
                .inNamespace(ns)
                .list()
                .getItems()
                .asScala
        }
        .filter(_.getSpec().getPorts().asScala.exists(_.getName() == "spark-ui"))

    handler.addServlet(new ServletHolder(new HelloServlet), "/hello")
    handler.addServlet(new ServletHolder(new HomeServlet(services)), "/*")

    server.start()
    server.join()
}
