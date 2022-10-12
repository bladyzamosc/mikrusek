from diagrams import Diagram, Cluster
from diagrams.c4 import System

with Diagram("Mikrusek - context diagram", show=False, filename="./assets/context"):
    webbrowser = System(name="Web client", external=True)
    thirdPartyClient = System("3-rd party application", external=True)
    mobileClient = System("Mobile", external=True)

    system = System("Mikrus", external=False)

    email = System("E-mail", external=True)
    thirdPartyService = System("Api Service", external=True)

    with Cluster("Clients"):
        clients = [webbrowser, thirdPartyClient, mobileClient]

    clients >> system
    system >> email
    system >> thirdPartyService
