package com.yjkim.spring.java.utility.json;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * JsonNode 타입 관련 유틸리티
 */
public class JsonNodeUtil
{
    /**
     * JsonNode 해당 경로의 String 값 반환
     *
     * @param node
     * @param fieldPaths 경로
     * @return 문자 값
     */
    public static String jsonNodeToString (JsonNode node, String... fieldPaths)
    {
        if (node == null || fieldPaths == null || fieldPaths.length == 0)
        {
            return null;
        }

        if (fieldPaths.length == 1)
        {
            return node.has(fieldPaths[0]) ? node.get(fieldPaths[0]).asText() : null;
        } else if (node.has(fieldPaths[0]))
        {
            JsonNode tmp = node.get(fieldPaths[0]);

            for (int i = 1; i < fieldPaths.length; i++)
            {
                if (tmp == null)
                {
                    return null;
                }

                if (tmp.has(fieldPaths[i]))
                {
                    tmp = tmp.get(fieldPaths[i]);
                }
            }

            return tmp != null ? tmp.asText() : null;
        }

        return null;
    }

    /**
     * JsonNode 해당 경로의 Boolean 값 반환
     *
     * @param node       노드
     * @param fieldPaths 경로
     * @return Boolean 값
     */
    public static Boolean jsonNodeToBoolean (JsonNode node, String... fieldPaths)
    {
        if (node == null || fieldPaths == null || fieldPaths.length == 0)
        {
            return null;
        }

        if (fieldPaths.length == 1)
        {
            return node.has(fieldPaths[0]) ? node.get(fieldPaths[0]).asBoolean() : null;
        } else if (node.has(fieldPaths[0]))
        {
            JsonNode tmp = node.get(fieldPaths[0]);

            for (int i = 1; i < fieldPaths.length; i++)
            {
                if (tmp == null)
                {
                    return null;
                }

                if (tmp.has(fieldPaths[i]))
                {
                    tmp = tmp.get(fieldPaths[i]);
                }
            }

            return tmp != null ? tmp.asBoolean() : null;
        }

        return null;
    }

    /**
     * JsonNode 해당 경로의 Long 값 반환
     *
     * @param node      노드
     * @param fieldPath 경로
     * @return Long 값
     */
    public static Long jsonNodeToLong (JsonNode node, String fieldPath)
    {
        if (node == null)
        {
            return null;
        }
        return node.has(fieldPath) ? node.get(fieldPath).asLong() : null;
    }
}
