from diagrams import Diagram, Cluster
from diagrams.aws.compute import ECS
from diagrams.aws.database import RDS
from diagrams.aws.network import APIGateway
from diagrams.onprem.queue import Kafka

with Diagram("Mikrusek - containers", show=False, filename="./assets/container"):
    apiGateway = APIGateway("Edge service")
    timeService = ECS("TimeSeries service")
    nodeService = ECS("Node service")

    timeSeriesDatabase = RDS("TS Storage")
    nodeDatabase = RDS("Node Storage")

    processingService = ECS("Processing service")
    kafka = Kafka("Message broker")

    configurationService = ECS("Configuration")
    serviceDiscovery = ECS("Service discovery")

    with Cluster("TS Cluster"):
        ts_cluster = timeService
        ts_cluster - timeSeriesDatabase

    with Cluster("Node Cluster"):
        node_cluster = nodeService
        node_cluster - nodeDatabase

    apiGateway >> ts_cluster
    apiGateway >> node_cluster
    apiGateway >> processingService >> kafka >> timeService
    kafka >> processingService
    processingService >> timeService

    apiGateway >> configurationService
    apiGateway >> serviceDiscovery

    timeService >> configurationService
    timeService >> serviceDiscovery

    processingService >> configurationService
    processingService >> serviceDiscovery

    nodeService >> configurationService
    nodeService >> serviceDiscovery