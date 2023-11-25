package com.bytesmart.admincenter.service;

import com.bytesmart.admincenter.domain.BytesmartConfig;

import java.util.List;

public interface IBytesmartConfigService {

    /**
     * 查询参数配置信息
     *
     * @param config 参数配置信息
     * @return 参数配置信息
     */
//    public BytesmartConfig selectConfig(BytesmartConfig config);

    /**
     * 通过ID查询配置
     *
     * @param configId 参数ID
     * @return 参数配置信息
     */
//    public BytesmartConfig selectConfigById(Long configId);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
//    public List<BytesmartConfig> selectConfigList(BytesmartConfig config);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数配置信息
     */
//    public BytesmartConfig checkConfigKeyUnique(String configKey);

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
//    public int insertConfig(BytesmartConfig config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
//    public int updateConfig(BytesmartConfig config);

    /**
     * 删除参数配置
     *
     * @param configId 参数ID
     * @return 结果
     */
//    public int deleteConfigById(Long configId);

    /**
     * 批量删除参数信息
     *
     * @param configIds 需要删除的参数ID
     * @return 结果
     */
//    public int deleteConfigByIds(Long[] configIds);
}
