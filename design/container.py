from diagrams import Diagram, Cluster, Edge
from diagrams.alibabacloud.compute import ElasticSearch
from diagrams.aws.management import Config
from diagrams.aws.migration import ADS
from diagrams.aws.network import APIGateway
from diagrams.azure.database import SQLDatabases, DataLake
from diagrams.azure.web import AppServices, Search
from diagrams.onprem.queue import Kafka

with Diagram("Mikrusek - containers", show=False, filename="./assets/container"):
    apiGateway = APIGateway("Edge service")
    timeService = AppServices("TimeSeries service")
    nodeService = AppServices("Node service")
    webBrowser = AppServices("Web client")

    timeSeriesDatabase = DataLake("TS Storage")
    nodeDatabase = SQLDatabases("Node Storage")

    processingService = AppServices("Processing service")
    kafka = Kafka("Event store")

    searchEngine = Search("Search engine")

    configurationService = Config("Configuration")
    serviceDiscovery = ADS("Service discovery")

    readConfigEdge = Edge(color='yellow')
    serviceDiscoveryEdge = Edge(color='grey')
    standartBiEdge = Edge(color='black', forward=True, reverse=True)
    standartEdge = Edge(color='black', forward=True)

    with Cluster("TS Cluster"):
        ts_cluster = timeService
        ts_cluster - timeSeriesDatabase

    with Cluster("Node Cluster"):
        node_cluster = nodeService
        node_cluster - nodeDatabase

    apiGateway >> standartBiEdge >> ts_cluster
    apiGateway >> standartBiEdge >> node_cluster
    apiGateway >> standartEdge >> processingService >> standartBiEdge >> kafka >> standartEdge >> timeService
    processingService >> standartEdge >> timeService

    webBrowser >> standartEdge >> apiGateway

    apiGateway >> readConfigEdge >> configurationService
    apiGateway >> serviceDiscoveryEdge >> serviceDiscovery

    timeService >> readConfigEdge >> configurationService
    timeService >> serviceDiscoveryEdge >> serviceDiscovery

    processingService >> readConfigEdge >> configurationService
    processingService >> serviceDiscoveryEdge >> serviceDiscovery

    nodeService >> readConfigEdge >> configurationService
    nodeService >> serviceDiscoveryEdge >> serviceDiscovery

    apiGateway >> standartBiEdge >> searchEngine
    searchEngine >> readConfigEdge >> configurationService
    searchEngine >> serviceDiscoveryEdge >> serviceDiscovery
