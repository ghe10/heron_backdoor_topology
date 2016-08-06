package(default_visibility = ["//visibility:public"])

java_binary(
    name='backdoor_topology_unshaded',
    srcs = glob(["**/*.java"]),
    deps = [
        "@com_twitter_heron_heron_storm//jar",
    ],
    create_executable = 0,
)

genrule(
    name = 'backdoor_topology',
    srcs = [":backdoor_topology_unshaded_deploy.jar"],
    outs = ["backdoor_topology.jar"],
    cmd  = "cp $< $@",
)
