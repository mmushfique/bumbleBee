<%
    // Set standard HTTP/1.1 no-cache headers.
    response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="bootstrap icons/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/common.css" />

    <body>
 <header id="header">
            <jsp:include page="header.jsp"/>
        </header>

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