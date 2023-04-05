<%
    HttpSession se = request.getSession();
    String user = (String) se.getAttribute("loggedUser");
    String role = (String) se.getAttribute("role");
    // Set standard HTTP/1.1 no-cache headers.
    response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="bootstrap icons/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/common.css" />

    <body>


        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand">

            </a>
            <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarMenu">
                <ul class="nav navbar-nav mx-auto">
                    <li class="nav-item">
                        <a href="index.jsp" class="nav-link">
                            <span class="bi bi-house-door mr-1"></span>
                            Home
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="services.jsp" class="nav-link">
                            <span class="bi bi-collection mr-1"></span>
                            Services
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="aboutus.jsp" class="nav-link">
                            <span class="bi bi-file-person mr-1"></span>
                            About
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="contact.jsp" class="nav-link">
                            <span class="bi bi-person-lines-fill mr-1"></span>
                            Contact
                        </a>
                    </li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="nav-item">
                        <a href="customerSignUp.jsp" class="nav-link">
                            <span class="bi bi-box-arrow-in-right mr-1"></span>
                            Signup as a customer
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <style>
            :root {
                scroll-behavior: smooth;
                --bgColor: #343a40;
                --color: white;
            }

            .nav-item a {
                transition: background-color 0.25s linear;
            }

            .nav-item a:hover {
                outline: 1px solid var(--color);
                background-color: var(--bgColor);
                color: var(--color) !important;
                outline-offset: -2px;
            }

            #navbarMenu {
                transition: 0.25s;
            }

            @media (max-width: 991px) {
                .nav li a {
                    text-align: center;
                }
            }
        </style>
        <script src="js/common.js"></script>
    </body>
</html>